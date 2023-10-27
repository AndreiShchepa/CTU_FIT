# Algorithm Comparison Project

This project is designed to compare the performance of two algorithms: `probSAT` and `GSAT`.

## Prerequisites

Before running the project, ensure the following:

1. You have both `probSAT` and `GSAT` algorithms available.
2. Unzip and place your sets of instances in the `data/` directory.

## Setup & Installation

1. **Virtual Environment Setup**:
    ```bash
    python3 -m venv venv
    source venv/bin/activate
    ```

2. **Install Dependencies**:
    ```bash
    pip install -r requirements.txt
    ```

3. **Navigate to Source Directory**:
    ```bash
    cd src/
    ```

## Usage

To run the main comparison script:

```bash
python3 main.py [--gsat_path YOUR_GSAT_PATH] [--probsat_path YOUR_PROBSAT_PATH] [--data_path YOUR_DATA_PATH]

Options:
-h, --help            
    Show this help message and exit.

--gsat_path GSAT_PATH
    Specify the path to the GSAT algorithm. (Default is the `gsat` path in the root directory)

--probsat_path PROBSAT_PATH
    Specify the path to the probSAT algorithm. (Default is the `probsat` path in the root directory)

--data_path DATA_PATH
    Specify the path to your data. (Default is the `data` directory in the root)
```

## Results

After running, you can find all the CSV results in the `results/` directory. All generated figures will be available in the `plots/` directory.

## Additional Utilities

To print merged statistics from the generated CSV files, run:

```bash
python3 tableData.py
```