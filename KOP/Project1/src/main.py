import os
import multiprocessing
import argparse

import cdf
import config
import histogram
import logNormalD
from csvMerger import CSVMerger
from algorithmEval import AlgorithmEvaluator


def parse_arguments():
    parser = argparse.ArgumentParser(description="Evaluate algorithms based on provided executable script.")

    default_args = {
        "gsat_path": f"{config.ROOT_DIR}/gsat",
        "probsat_path": f"{config.ROOT_DIR}/probsat",
        "data_path": f"{config.ROOT_DIR}/data"
    }

    for arg, default in default_args.items():
        parser.add_argument(
            f"--{arg}",
            type=str,
            default=default,
            help=f"Path to the {arg.replace('_', ' ')}"
        )

    return parser.parse_args()


def create_directories():
    for directory in [config.RESULTS, config.MERGED, config.OUTPUT_DIR]:
        os.makedirs(directory, exist_ok=True)


def evaluate_algorithms(probsat_path, gsat_path, data_path):
    probsat_evaluator = AlgorithmEvaluator(probsat_path, data_path)
    gsat_evaluator = AlgorithmEvaluator(gsat_path, data_path)

    p1 = multiprocessing.Process(target=probsat_evaluator.run, args=("probSAT",))
    p2 = multiprocessing.Process(target=gsat_evaluator.run, args=("GSAT",))

    p1.start()
    p2.start()

    p1.join()
    p2.join()


def main():
    create_directories()
    args = parse_arguments()

    evaluate_algorithms(args.probsat_path, args.gsat_path, args.data_path)

    combiner = CSVMerger()
    combiner.process_files()

    cdf.cdf()
    histogram.histogram()
    logNormalD.lognormal()


if __name__ == "__main__":
    main()
