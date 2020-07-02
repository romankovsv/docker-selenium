package com.serachmodule.com;

import base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import searchmodule.pages.SearchPage;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest{

    @Test
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        assertTrue(searchPage.returnNumberOfVideos() > 0);
    }

}
