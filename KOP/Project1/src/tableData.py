import os
import pandas as pd
import config

COLUMNS = {
    "Solved probSAT": "sum",
    "Solved GSAT": "sum",
    "Awg fined steps probSAT": "avg",
    "Awg fined steps GSAT": "avg",
    "Awg steps of solved probSAT": "avg",
    "Awg steps of solved GSAT": "avg"
}


def print_results_for_file(filepath):
    df = pd.read_csv(filepath, delimiter=";")
    filename = os.path.basename(filepath)
    directory = os.path.dirname(filepath)

    print(f"Results for {filename} in {directory}:")
    for column, operation in COLUMNS.items():
        if operation == "sum":
            print(f"Total {column}: {df[column].sum()}")
        elif operation == "avg":
            print(f"Average {column}: {df[column].mean()}")
    print("-" * 40)


def main():
    for directory in [f"{config.RESULTS}/{set_name}" for set_name in config.SETS]:
        for filename in os.listdir(directory):
            filepath = os.path.join(directory, filename)
            print_results_for_file(filepath)


if __name__ == "__main__":
    main()
