# tests/test_selenium_app.py

import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.mark.smoke
def test_google_title(driver):
    """Verify Google home page title"""
    driver.get("https://www.google.com")

    # Wait up to 10 seconds for title to include "Google"
    WebDriverWait(driver, 10).until(EC.title_contains("Google"))
    assert "Google" in driver.title

@pytest.mark.regression
def test_google_search(driver):
    """Verify Google search functionality"""
    driver.get("https://www.google.com")
    
    search_box = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.NAME, "q"))
    )
    search_box.clear()
    search_box.send_keys
