package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class HCMChangeMaritalStatusTest extends HCMBase{
    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/02/2018", version = "1.0")
    @TestDesc(desc = "Test Change marital rule")
    public void ChangeMaritalstatus(){
        changemaritalrule(faEnvVersion);

    }

    }


