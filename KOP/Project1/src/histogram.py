import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import config


def get_all_csv_files(directory_path):
    return [os.path.join(directory_path, f) for f in os.listdir(directory_path) if f.endswith('.csv')]


def calculate_solved_percentages(all_files):
    solved_gsat_percentage = []
    solved_probsat_percentage = []

    for file in all_files:
        df = pd.read_csv(file, delimiter=config.DELIMITER)
        gsat_solved = (df[config.COLUMNS_HISTOGRAM[0]].sum() / df[config.COLUMNS_HISTOGRAM[2]].sum()) * 100
        probsat_solved = (df[config.COLUMNS_HISTOGRAM[1]].sum() / df[config.COLUMNS_HISTOGRAM[2]].sum()) * 100

        solved_gsat_percentage.append(gsat_solved)
        solved_probsat_percentage.append(probsat_solved)

    return solved_gsat_percentage, solved_probsat_percentage


def plot_solved_histogram(directory_path, all_files, gsat_percentages, probsat_percentages):
    index = np.arange(len(gsat_percentages))
    plt.figure(figsize=(15, 7))

    plt.bar(index, gsat_percentages, config.BAR_WIDTH_HISTOGRAM,
            color=config.COLORS[0], label='GSAT')
    plt.bar(index + config.BAR_WIDTH_HISTOGRAM, probsat_percentages,
            config.BAR_WIDTH_HISTOGRAM, color=config.COLORS[1], label='probSAT')

    file_labels = [
        os.path.basename(f).split('SET.')[1].split('_CONF')[0]
        .replace('-i_', '-i ').replace('-T_', '-T ')
        for f in all_files
    ]

    plt.xlabel('Configurations')
    plt.ylabel('Percentage of Solved Problems')
    plt.xticks(index + config.BAR_WIDTH_HISTOGRAM / 2, file_labels, rotation=0)

    plt.legend(loc='center left', bbox_to_anchor=(1, 0.5))

    plt.tight_layout()
    output_path = f'{config.OUTPUT_DIR}/histogram_{os.path.basename(directory_path)}.pdf'
    plt.savefig(output_path, bbox_inches='tight', format='pdf')


def histogram():
    for dir_set in [f'{config.MERGED}/{mydir}' for mydir in config.SETS]:
        all_files = get_all_csv_files(dir_set)
        gsat_percentages, probsat_percentages = calculate_solved_percentages(all_files)
        plot_solved_histogram(dir_set, all_files, gsat_percentages, probsat_percentages)
