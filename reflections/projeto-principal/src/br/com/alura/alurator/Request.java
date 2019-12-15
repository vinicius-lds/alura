package br.com.alura.alurator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private String url;
    private String controllerClassName;
    private String methodName;
    private Map<String, Object> keyValueParams = new HashMap<>();

    public Request(String url) {
        setUrl(url);
    }

    public void setUrl(String url) {
        if (url == null) {
            throw new NullPointerException("Url cannot be null.");
        } else {
            this.url = url;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getControllerClassName() {
        processRequestIfNeeded();
        return controllerClassName;
    }

    public void setControllerClassName(String controllerClassName) {
        this.controllerClassName = controllerClassName;
    }

    public String getMethodName() {
        processRequestIfNeeded();
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Object> getParams() {
        processRequestIfNeeded();
        return this.keyValueParams;
    }

    private boolean isRequestProcessed() {
        return controllerClassName != null && methodName != null;
    }

    private void processRequestIfNeeded() {
        if (!isRequestProcessed()) {
            processRequest();
        }
    }

    private void processRequest() {
        var urlSplit = splitUrl();
        if (urlSplit.size() > 0) {
            var controllerClassName = urlSplit.get(0) + "Controller";
            setControllerClassName(controllerClassName);
            processControllerClassName();
        }
        if (urlSplit.size() > 1) {
            var methodName = urlSplit.get(1);
            setMethodName(methodName);
        }
    }

    private void processControllerClassName() {
        if (controllerClassName == null) {
            throw new NullPointerException("ControllerClassName is null");
        } else {
            var sb = new StringBuilder(controllerClassName);
            if (sb.charAt(0) > 96) {
                sb.setCharAt(0, ((char) (sb.charAt(0) - 32)));
            }
            controllerClassName = sb.toString();
        }
    }

    private List<String> splitUrl() {
        var result = new ArrayList<String>();
        var slashSplit = this.url.startsWith("/") ? this.url.replaceFirst("/", "").split("/") : this.url.split("/");
        if (slashSplit.length > 0) {
            result.add(slashSplit[0]);
        }
        if (slashSplit.length > 1) {
            var methodName = slashSplit[1];
            var methodNameSplit = methodSplit(methodName);
            result.add(methodNameSplit.get(0));
        }
        return result;
    }

    private List<String> methodSplit(String methodName) {
        var list = new ArrayList<String>();
        var originalSplit = methodName.split("\\?");
        if (originalSplit.length > 0) {
            list.add(originalSplit[0]);
        }
        if (originalSplit.length > 1) {
            var namesParams = originalSplit[1];
            var namedParamsSplit = namedParamsSplit(namesParams);
            for (String namedParam : namedParamsSplit) {
                var keyValueParam = keyValueParamSplit(namedParam);
                String key = null;
                Object value = null;
                if (keyValueParam.length > 0) {
                    key = keyValueParam[0];
                }
                if (keyValueParam.length > 1) {
                    value = keyValueParam[1];
                }
                keyValueParams.put(key, value);
            }
        }
        return list;
    }

    private String[] keyValueParamSplit(String keyValueParam) {
        return keyValueParam != null ? keyValueParam.split("=") : null;
    }

    private String[] namedParamsSplit(String namedParams) {
        return namedParams != null ? namedParams.split("&") : null;
    }

}
