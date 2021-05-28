package com.scale.bat.framework.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	 private Properties properties;
	    private String path = "config//ApplicationConfig.properties";
	    public ConfigurationReader()
	    {
	        BufferedReader reader;
	        try {
	            reader = new BufferedReader(new FileReader(path));
	            properties = new Properties();
	            try {
	                properties.load(reader);
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException("ApplicationConfig.properties not found at " + path);
	        }
	    }

	    public String getApplicationURL()
	    {
	        String URL = properties.getProperty("appURL");
	        if(URL!= null)
	            return URL;
	        else
	            throw new RuntimeException("Application URL not specified in the ApplicationConfig.properties file.");
	    }

	    public String getChromeDriverPath(){
	        String path = properties.getProperty("chromeDriverpath");
	        if(path!= null) {
	            String osname = System.getProperty("os.name").toLowerCase();
	            if(osname.contains("mac"))
	                return path;
	            else
	                return path+".exe";
	        }
	        else
	            throw new RuntimeException("Chrome driver path not specified in the ApplicationConfig.properties file.");
	    }

	    public String getGeckoDriverPath(){
	        String path = properties.getProperty("geckoDriverpath");
	        if(path!= null)
	        {
	            String osname = System.getProperty("os.name").toLowerCase();
	            if(osname.contains("mac"))
	                return path;
	            else
	                return path+".exe";
	        }
	        else
	            throw new RuntimeException("Gecko driver path not specified in the ApplicationConfig.properties file.");
	    }

	    public String getSecurityKey()
	    {
	        String securityKey = properties.getProperty("securityKey");
	        if(securityKey!= null)
	            return securityKey;
	        else
	            throw new RuntimeException("Security Key not specified in the ApplicationConfig.properties file.");
	    }

	    public String getPIN()
	    {

	        String pin = properties.getProperty("PIN");
	        if(pin!= null)
	            return pin;
	        else
	            throw new RuntimeException("PIN not specified in the ApplicationConfig.properties file.");

	    }

	    public String getBrowserName()
	    {

	        String browserName = properties.getProperty("browserName");
	        if(browserName!= null)
	            return browserName;
	        else
	            return "";

	    }

	    public String get(String key)
	    {
	        String value = properties.getProperty(key);
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(key + " not specified in the ApplicationConfig.properties file.");
	    }

	    public String adminPanelUrl()
	    {
	        String value = properties.getProperty("ccs.admin.panel.url");
	        System.out.println(value);
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String adminPanelUserName(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.username.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    
	    
	    public String adminPanelPassword(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.password.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String buyerUIUrl()
	    {
	        String value = properties.getProperty("ccs.buyer.ui.url");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String buyerUserName()
	    {
	        String value = properties.getProperty("ccs.buyer.panel.username");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String buyerpassword()
	    {
	        String value = properties.getProperty("ccs.buyer.panel.password");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String awsS3fileUploadBucket()
	    {
	        String value = properties.getProperty("ccs.amazon.s3.bucketName");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String awsS3fileUploadFolder()
	    {
	        String value = properties.getProperty("ccs.amazon.s3.SFTP.newFileUpload");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String adminPanelSupplierName(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.username.userrolename."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    public String postgressqlPort()
	    {
	        String value = properties.getProperty("ccs.postgres.port");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    public String postgressqlServer()
	    {
	        String value = properties.getProperty("ccs.postgres.server");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    public String postgressqlDataBaseName()
	    {
	        String value = properties.getProperty("ccs.postgres.database");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    public String postgressqlUser()
	    {
	        String value = properties.getProperty("ccs.postgres.user");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    public String postgressqlPassword()
	    {
	        String value = properties.getProperty("ccs.postgres.password");
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    //API UserName
	    public String apiUserName(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.apiusername.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    //API User Password
	    public String apiUserPassword(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.password.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	    //Checkout user
	    public String adminPanelCheckoutUserName(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.username.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
	  //Checkout User Password
	    public String adminPanelCheckoutUserPassword(String role)
	    {
	        String value = properties.getProperty("ccs.admin.panel.password.userrole."+role.toLowerCase());
	        if(value!= null)
	            return value;
	        else
	            throw new RuntimeException(" Key not specified in the ApplicationConfig.properties file.");
	    }
	    
}