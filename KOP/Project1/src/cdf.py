import os
import config
import pandas as pd
import matplotlib.pyplot as plt


def calculate_cdf(data):
    sorted_data = sorted(data)
    cdf_values = [i/len(sorted_data) for i in range(len(sorted_data))]
    return sorted_data, cdf_values


def plot_cdf(data, label, color):
    x, y = calculate_cdf(data)
    plt.plot(x, y, label=label, color=color)


def extract_values_from_file(file, column):
    df = pd.read_csv(file, delimiter=config.DELIMITER)
    return df[column].round().astype(int).tolist()


def cdf():
    for i, dir_set in enumerate([f'{config.MERGED}/{mydir}' for mydir in config.SETS]):
        all_files = [os.path.join(dir_set, f) for f in os.listdir(dir_set)]
        all_gsat_values = []
        all_probsat_values = []

        for file in all_files:
            all_gsat_values.extend(extract_values_from_file(file, config.COLUMNS_CDF[0]))
            all_probsat_values.extend(extract_values_from_file(file, config.COLUMNS_CDF[1]))

        plt.figure(figsize=(10, 6))

        plot_cdf(all_gsat_values, config.COLUMNS_CDF[0], config.COLORS[0])
        plot_cdf(all_probsat_values, config.COLUMNS_CDF[1], config.COLORS[1])

        plt.xlabel('Avg fined steps')
        plt.ylabel('Probability')
        plt.legend()
        plt.grid(True, which='both', linestyle='--', linewidth=0.5)
        plt.tight_layout()

        output_path = f'{config.OUTPUT_DIR}/cdf-{config.SETS[i]}.pdf'
        plt.savefig(output_path, bbox_inches='tight', format='pdf')
