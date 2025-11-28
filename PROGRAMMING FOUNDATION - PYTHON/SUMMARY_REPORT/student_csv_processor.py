import csv

def read_csv(ip_csv):
    try:
        with open(ip_csv, "r", newline="") as file:
            reader = csv.DictReader(file)
            data = list(reader)
            fields={"Name","Roll No","Marks"}
            if not fields.issubset(reader.fieldnames):
                raise ValueError(f"FEW FIELDS ARE MISSING IN {file}")

        # Validate required columns
        if "Name" not in reader.fieldnames or "Marks" not in reader.fieldnames:
            raise ValueError(f"{ip_csv} must contain 'Name' and 'Marks' columns")

        # Convert Marks to integer and validate
        for row in data:
            try:
                row["Marks"] = int(row["Marks"])
            except ValueError:
                raise ValueError("Marks value should be integer")

        return data

    except FileNotFoundError:
        print(f" File '{ip_csv}' not found.")
        return
    except ValueError as ve:
        print(f"Error: {ve}")
        return
    except Exception as e:
        print(f"Unexpected Error: {e}")
        return


def calculate_statistics(data):
    marks_list = [row["Marks"] for row in data]

    total = sum(marks_list)
    avg = total / len(marks_list)
    max_marks = max(marks_list)

    toppers = [row["Name"] for row in data if row["Marks"] == max_marks]

    summary = {
        "Average Marks": round(avg, 2),
        "Highest Marks": max_marks,
        "Topper(s)": ", ".join(toppers)
    }

    return summary


def write_summary(op_csv, summary):
    try:
        with open(op_csv, "w", newline="") as file:
            writer = csv.DictWriter(file, fieldnames=summary.keys())
            writer.writeheader()
            writer.writerow(summary)

        print(f" Summary exported to {op_csv}")
    except Exception as e:
        print(f" Error writing summary file: {e}")


if __name__ == "__main__":
    ip_csv = "students.csv"
    op_csv = "summary.csv"

    data = read_csv(ip_csv)

    if data:  # ensure not None
        summary = calculate_statistics(data)
        write_summary(op_csv, summary)

        print("\n====== Summary Report =====")
        for key, value in summary.items():

           print(f"{key}: {value}")

