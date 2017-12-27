package trapx00.lightx00.server.data.logdata;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.j256.ormlite.dao.Dao;
import trapx00.lightx00.server.Server;
import trapx00.lightx00.server.data.logdata.factory.LogDataDaoFactory;
import trapx00.lightx00.server.data.util.serverlogservice.ServerLogService;
import trapx00.lightx00.server.data.util.serverlogservice.factory.ServerLogServiceFactory;
import trapx00.lightx00.shared.dataservice.logdataservice.LogBackupDataService;
import trapx00.lightx00.shared.exception.database.DbSqlException;
import trapx00.lightx00.shared.exception.oos.CannotOpenTempFileException;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.log.LogPo;
import trapx00.lightx00.shared.queryvo.LogBackupVo;
import trapx00.lightx00.shared.util.DateHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogBackupDataController extends UnicastRemoteObject implements LogBackupDataService {
    private static final String END_POINT = "http://oos.ctyunapi.cn";
    private static final String BUCKET_NAME = "lightx00";
    private static final String ACCESS_KEY = "622ff0aad8c78a306eaa";
    private static final String SECRET_KEY = "c4a84bcc4ce1ad09805def0284a07452dd7a7519";
    private static final String FILE_PATH = Server.class.getResource("/temp.txt").getPath();
    private static final String SEPATAROR = " | ";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    private Dao<LogPo, Integer> logDao = LogDataDaoFactory.getLogDao();
    private Object delegate = this;
    private ServerLogService logService = ServerLogServiceFactory.getService();

    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     * The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    public LogBackupDataController() throws RemoteException {
    }

    private void handleSQLException(SQLException e) {
        logService.printLog(delegate, "failed at a database operation. Error message: " + e.getMessage());
        throw new DbSqlException(e);
    }

    private void handleIOException(IOException e) {
        logService.printLog(delegate, "failed at a io operation. Error message: " + e.getMessage());
        throw new CannotOpenTempFileException();
    }

    /**
     * back up the log information on the server to oos and clear the log
     *
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage backupLog() {
        try {
            List<LogPo> logPos = logDao.queryBuilder().query();
            File file = new File(FILE_PATH);
            FileWriter fileWriter = new FileWriter(file);
            for (LogPo logPo : logPos) {
                fileWriter.write(logPo.getId() + SEPATAROR);
                fileWriter.write(logPo.getSeverity().toString() + SEPATAROR);
                fileWriter.write(DateHelper.fromDate(logPo.getDate()) + SEPATAROR);
                fileWriter.write(logPo.getContent() + System.lineSeparator());
            }
            fileWriter.flush();
            fileWriter.close();
            logDao.delete(logPos);

            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonS3 oos = new AmazonS3Client(credentials);
            oos.setEndpoint(END_POINT);
            oos.putObject(BUCKET_NAME, DateHelper.currentDateString("yyyy_MM_dd_HH:mm:ss"), file);
            return ResultMessage.Success;
        } catch (SQLException e) {
            e.printStackTrace();
            handleSQLException(e);
            return ResultMessage.Failure;
        } catch (IOException e) {
            e.printStackTrace();
            handleIOException(e);
            return ResultMessage.Failure;
        }
    }

    /**
     * get the log backed up on the cloud
     *
     * @return the temp uri of the log resources
     */
    @Override
    public LogBackupVo[] fetchCloudLog() {
        ArrayList<LogBackupVo> result = new ArrayList<>();
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3 oos = new AmazonS3Client(credentials);

        oos.setEndpoint(END_POINT);
        List<S3ObjectSummary> list = oos.listObjects(BUCKET_NAME).getObjectSummaries();
        for (S3ObjectSummary object : list) {
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(BUCKET_NAME, object.getKey());
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(new Date(EXPIRATION));
            URL url = oos.generatePresignedUrl(generatePresignedUrlRequest);
            LogBackupVo logBackupVo = new LogBackupVo(object.getKey(), url.toString());
            logService.printLog(delegate, "fetch cloud log:" + logBackupVo.getDate() + "|" + logBackupVo.getUrl());
            result.add(logBackupVo);
        }
        return result.toArray(new LogBackupVo[result.size()]);
    }
}
