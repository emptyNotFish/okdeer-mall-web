package com.okdeer.mall.exception;
/**
 * 自定义异常 获取异常的详细信息
 * @author Administrator
 *
 */
public class CmsDownloadException extends Exception {
    private static final long serialVersionUID = 96237909901082988L;
    /**
     * 状态码说明。
     */
    private String retCode;
    /**
     * 状态码说明。
     */
    private String retCodeDescription;
    /**
     * Construct a <code>CmsException</code> with the specified detail message.
     *
     * @param code
     *            code
     */
    public CmsDownloadException(String code) {
        // super(msg);
        super(ResponseCode.getErrorMessage(code));
        this.retCode = code;
        this.retCodeDescription = ResponseCode.getErrorMessage(code);
    }
    public CmsDownloadException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public String getRetCode() {
        return retCode;
    }
    /*
     * 状态码说明。
     *
     * @return {@link String} 详细信息
     */
    public String getRetCodeDescription() {
        return this.retCodeDescription;
    }
    /**
     * 取异常的详细信息。
     *
     * @return 详细信息
     */
    public String getDetailedMessage() {
        if (getCause() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(toString()).append("; ");
            if (getCause() instanceof CmsDownloadException) {
                sb.append(((CmsDownloadException) getCause()).getDetailedMessage());
            } else {
                sb.append(getCause());
            }
            return sb.toString();
        } else {
            return super.toString();
        }
    }
    /**
     * 取异常的详细信息。
     *
     * @return 详细信息
     */
    public Throwable getRootCause() {
        Throwable rootCause = this;
        Throwable cause = getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }
    /**
     * 取异常的详细信息。
     *
     * @return 详细信息
     */
    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }
    /**
     * 取异常的详细信息。
     *
     * @param exType
     *            是否包含类
     *
     * @return true或false
     */
    public boolean contains(Class<?> exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof CmsDownloadException) {
            return ((CmsDownloadException) cause).contains(exType);
        } else {
            while (cause != null) {
                if (exType.isInstance(cause)) {
                    return true;
                }
                if (cause.getCause() == cause) {
                    break;
                }
                cause = cause.getCause();
            }
            return false;
        }
    }
}

