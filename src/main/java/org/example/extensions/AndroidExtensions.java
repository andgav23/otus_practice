package org.example.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.example.drivers.AndroidSelenideDriver;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AndroidExtensions implements BeforeAllCallback {
  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {

    Configuration.browserSize = null;
    Configuration.browser = AndroidSelenideDriver.class.getName();
    Configuration.downloadsFolder = null;
    Configuration.fileDownload = FileDownloadMode.HTTPGET;
    Configuration.reportsFolder = "target/selenid-reports";
  }

}
