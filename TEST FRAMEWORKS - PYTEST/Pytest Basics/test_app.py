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
