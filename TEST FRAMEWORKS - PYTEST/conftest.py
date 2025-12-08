# conftest.py

import pytest
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import os

# ===========================
# Sample Data Fixtures
# ===========================

# Function-scoped fixture (default)
@pytest.fixture
def sample_numbers():
    data = {"a": 2, "b": 3, "even_num": 4, "odd_num": 7}
    print("\n[Setup] function-scoped sample_numbers")
    yield data
    print("\n[Teardown] function-scoped sample_numbers")

# Class-scoped fixture
@pytest.fixture(scope="class")
def class_setup():
    print("\n[Setup] class-scoped setup")
    yield
    print("\n[Teardown] class-scoped setup")

# Module-scoped fixture
@pytest.fixture(scope="module")
def module_setup():
    print("\n[Setup] module-scoped setup")
    yield
    print("\n[Teardown] module-scoped setup")

# Session-scoped fixture
@pytest.fixture(scope="session")
def session_setup():
    print("\n[Setup] session-scoped setup")
    yield
    print("\n[Teardown] session-scoped setup")

# ===========================
# Selenium WebDriver Fixture
# ===========================

@pytest.fixture
def driver():
    """Initialize Chrome WebDriver for Selenium tests"""
    # Optional: add Chrome options
    options = Options()
    options.add_argument("--start-maximized")
    # options.add_argument("--headless")  # Uncomment for headless mode

    # Initialize driver
    driver = webdriver.Chrome(options=options)
    yield driver
    driver.quit()

# ===========================
# Hook for Screenshots on Failure
# ===========================

@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    """Capture screenshot on test failure if driver fixture is used"""
    outcome = yield
    report = outcome.get_result()

    if report.failed and "driver" in item.fixturenames:
        driver = item.funcargs["driver"]
        # Ensure folder exists
        os.makedirs("reports/screenshots", exist_ok=True)
        screenshot_path = f"reports/screenshots/{item.name}.png"
        driver.save_screenshot(screenshot_path)
        print(f"\n[Screenshot] saved: {screenshot_path}")
