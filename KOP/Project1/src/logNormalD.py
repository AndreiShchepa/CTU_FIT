import os
import config
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import lognorm


def extract_values_from_file(file, column):
    df = pd.read_csv(file, delimiter=config.DELIMITER)
    return df[column].round().astype(int).tolist()


def extract_values_from_files(files, column):
    values = []
    for file in files:
        values.extend(extract_values_from_file(file, column))
    return values


def calculate_lognormal_params(values):
    mu, sigma = np.mean(np.log(values)), np.std(np.log(values))
    return mu, sigma


def plot_lognormal_distribution(x, mu, sigma, label, color):
    plt.plot(x, lognorm.pdf(x, sigma, scale=np.exp(mu)), label=label, color=color)


def lognormal():
    for i, dir_set in enumerate([f'{config.MERGED}/{mydir}' for mydir in config.SETS]):
        all_files = [os.path.join(dir_set, f) for f in os.listdir(dir_set)]
        all_values = [extract_values_from_files(all_files, col) for col in config.COLUMNS_LOGNORMAL]
        mus, sigmas = zip(*[calculate_lognormal_params(values) for values in all_values])

        x = np.linspace(0.1, max(max(values) for values in all_values), 1000)

        plt.figure(figsize=(10, 6))

        for j, col in enumerate(config.COLUMNS_LOGNORMAL):
            plot_lognormal_distribution(x, mus[j], sigmas[j], col, config.COLORS[j])

        plt.xlabel('Avg steps for solved (log scale)')
        plt.ylabel('Probability Density')
        plt.legend()
        plt.grid(True)
        plt.tight_layout()

        output_path = f'{config.OUTPUT_DIR}/lognormal-{config.SETS[i]}.pdf'
        plt.savefig(output_path, bbox_inches='tight', format='pdf')