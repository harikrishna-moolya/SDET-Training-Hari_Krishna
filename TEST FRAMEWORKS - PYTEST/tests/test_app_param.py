import pytest
import csv
from app import add, is_even

# Load CSV data
def load_csv_data(filepath):
    with open(filepath, newline='') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # skip header
        return [(int(row[0]), int(row[1]), int(row[2])) for row in reader]

# Parametrize tests using CSV
@pytest.mark.parametrize("a,b,expected", load_csv_data("tests/add_data.csv"))
def test_add_from_csv(a, b, expected):
    assert add(a, b) == expected

# Fixture-based tests
@pytest.fixture(params=[{"a": 2, "b": 3, "even_num": 4}, {"a": 5, "b": 5, "even_num": 10}])
def sample_numbers(request):
    return request.param

def test_add_with_fixture(sample_numbers):
    a = sample_numbers["a"]
    b = sample_numbers["b"]
    assert add(a, b) == a + b

def test_add_and_even_combined(sample_numbers):
    a = sample_numbers["a"]
    b = sample_numbers["b"]
    even_num = sample_numbers["even_num"]
    assert add(a, b) == a + b
    assert is_even(even_num) is True
