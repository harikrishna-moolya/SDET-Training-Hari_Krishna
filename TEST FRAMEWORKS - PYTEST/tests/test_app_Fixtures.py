import pytest
from app import add, is_even

# Apply module & session scoped fixtures to the whole class
@pytest.mark.usefixtures("module_setup", "session_setup")
class TestApp:

    # Apply class-scoped fixture for all tests in this class
    @pytest.mark.usefixtures("class_setup")
    @pytest.mark.smoke
    def test_add_numbers(self, sample_numbers):
        # sample_numbers is function-scoped
        assert add(sample_numbers["a"], sample_numbers["b"]) == 5
        assert add(1, 1) == 2

    @pytest.mark.regression
    def test_is_even(self, sample_numbers):
        assert is_even(sample_numbers["even_num"]) is True
        assert is_even(sample_numbers["odd_num"]) is False

    @pytest.mark.smoke
    @pytest.mark.regression
    def test_add_and_even_combined(self, sample_numbers):
        # Using multiple fixtures and combining checks
        result = add(sample_numbers["a"], sample_numbers["b"])
        assert result == 5
        assert is_even(sample_numbers["even_num"]) is True
