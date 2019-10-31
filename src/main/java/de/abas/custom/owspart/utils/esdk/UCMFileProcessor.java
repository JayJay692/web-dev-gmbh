package de.abas.custom.owspart.utils.esdk;

import de.abas.erp.axi.event.EventException;

public class UCMFileProcessor {

    private FileContentReplacer fileContentReplacer = new FileContentReplacer();

    public void replaceCommandInUCMFile(String command) throws EventException {
        fileContentReplacer.replaceContentInFile(EsdkProperties.getUcmFile(), EsdkProperties.getUcmPlaceHolder(), command, "UTF-16");
    }
}
