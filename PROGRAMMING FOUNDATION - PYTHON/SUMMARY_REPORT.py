import sys
import pandas as pd

def read_csv(ip_csv):
    try:
        df = pd.read_csv(ip_csv)
        
        if "Name" not in df.columns or "Marks" not in df.columns:
            raise ValueError(f"{ip_csv} must contain 'Name' and 'Marks' columns")

        if not pd.api.types.is_numeric_dtype(df["Marks"]):
            raise ValueError("Marks value should be integer")

        return df

    except FileNotFoundError:
        print(f" File '{ip_csv}' not found.")
        sys.exit()
    except ValueError as ve:
        print(f"Error: {ve}")
        sys.exit()
    except Exception as e:
        print(f"Unexpected Error: {e}")
        sys.exit()



def calculate_statistics(df):
    total = df["Marks"].sum()
    avg = total / len(df)
    max_marks = df["Marks"].max()
    toppers = df[df["Marks"] == max_marks]["Name"].tolist()

    summary = {
        "Total Students": len(df),
        "Average Marks": round(avg, 2),
        "Highest Marks": max_marks,
        "Topper(s)": ", ".join(toppers)
    }

    return summary


def write_summary(op_csv, summary):
    try:
        summary_df = pd.DataFrame([summary])
        summary_df.to_csv(op_csv, index=False)
        print(f" Summary exported to {op_csv}")
    except Exception as e:
        print(f" Error writing summary file: {e}")


if __name__ == "__main__":
    ip_csv = "student.csv"
    op_csv = "student_summary.csv"

    data = read_csv(ip_csv)
    summary = calculate_statistics(data)
    write_summary(op_csv, summary)

    print("\n====== Summary Report =====")
    for key, value in summary.items():
        print(f"{key}: {value}")
