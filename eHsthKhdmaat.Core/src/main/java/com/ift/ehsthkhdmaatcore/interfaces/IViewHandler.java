package com.ift.ehsthkhdmaatcore.interfaces;

/**
 * Created by zahmed on 3/21/2018.
 */

public interface IViewHandler {
    public void setFocus();
    public void setErrorText(String errorMessage);
    public void setLocalizedError(String resourceKey);
}
