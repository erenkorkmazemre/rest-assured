package restapi;

import restapi.config.BaseConfiguration;

import java.io.File;

public class Util {

    BaseConfiguration baseConfiguration = new BaseConfiguration();

    public String getProperty(String property, boolean addEnv) {

        if (addEnv)
            return baseConfiguration.getProperty(getEnvironment() + "-" + property);
        else
            return baseConfiguration.getProperty(property);
    }

    public String getEnvironment() {

        if (System.getProperty("env") != null)
            return System.getProperty("env");
        else
            return baseConfiguration.getProperty("env");
    }

    public String getURL() {

        if (System.getProperty("URL") != null)
            return System.getProperty("URL");
        else
            return getProperty("URL", true);
    }

    public String getToken() {

        if (System.getProperty("TOKEN") != null)
            return System.getProperty("TOKEN");
        else
            return getProperty("TOKEN", true);
    }

    public String setToken(String string) {

        return System.setProperty("ACCESS_TOKEN", string);
    }

    public String getFilePath(String filePath) {

        String OS = System.getProperty("os.name");
        String result;

        if (OS.startsWith("Windows"))
            result = filePath;
        else
            result = filePath.replace("\\", File.separator);

        return result;
    }
}
