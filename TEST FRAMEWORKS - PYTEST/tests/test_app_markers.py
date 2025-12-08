import pytest
from app import add, is_even

@pytest.mark.smoke
def test_add_numbers():
    assert add(1, 1) == 2
    assert add(2, 3) == 5

@pytest.mark.regression
def test_is_even():
    assert is_even(4) is True
    assert is_even(7) is False

@pytest.mark.skip(reason="Skipping temporarily")
def test_skipped():
    assert add(10, 5) == 15  # This will not run

@pytest.mark.xfail(reason="Known bug in is_even function")
def test_unstable_is_even():
    # Let's assume is_even fails for 0 in your app
    assert is_even(0) is False
