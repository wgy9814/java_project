package cn.itcast.generate.entity;



//数据库实体类
public class DataBase {
                                     //             127.0.0.1：3306/ihrm
    private static String mysqlUrl = "jdbc:mysql://[ip]:[port]/[db]?useUnicode=true&amp;characterEncoding=UTF8";
    private static String oracleUrl = "jdbc:oracle:thin:@[ip]:[port]:[db]";

    private String dbType;//数据库类型
    private String userName;
    private String passWord;


    private String driver;
    private String url;

    public DataBase() {}

    public DataBase(String dbType) {
        this(dbType,"127.0.0.1","3306","");
    }

    public DataBase(String dbType,String db) {
        this(dbType,"127.0.0.1","3306",db);
    }


    /**
     *
     * @param dbType        数据库类型
     * @param ip            ip
     * @param port          3306
     * @param db            ihrm
     */
    public DataBase(String dbType,String ip,String port,String db) {
        this.dbType = dbType;
        if("MYSQL".endsWith(dbType.toUpperCase())) {
            this.driver="com.mysql.jdbc.Driver";
            this.url=mysqlUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }else{
            this.driver="oracle.jdbc.driver.OracleDriver";
            this.url=oracleUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
