import os
import pandas as pd
import config

COLUMNS = {
    "Solved probSAT": "sum",
    "Solved GSAT": "sum",
    "Avg fined steps probSAT": "avg",
    "Avg fined steps GSAT": "avg",
    "Avg steps of solved probSAT": "avg",
    "Avg steps of solved GSAT": "avg"
}


def print_results_for_file(filepath):
    df = pd.read_csv(filepath, delimiter=config.DELIMITER)
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
    for directory in [f"{config.MERGED}/{set_name}" for set_name in config.SETS]:
        for filename in os.listdir(directory):
            filepath = os.path.join(directory, filename)
            print_results_for_file(filepath)


if __name__ == "__main__":
    main()
