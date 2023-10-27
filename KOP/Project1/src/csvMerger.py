import os
import pandas as pd
import config


class CSVMerger:
    def __init__(self, directory_path=f'{config.ROOT_DIR}/results'):
        self.directory_path = directory_path

    def _merge_dataframes(self, gsat_df, probsat_df):
        columns = [item for g, p in zip(gsat_df.columns, probsat_df.columns)
                   for item in ([p, g] if g != p else [p])]
        target_df = pd.DataFrame(columns=columns)

        for col in target_df.columns:
            target_df[col] = gsat_df[col].values if col in gsat_df.columns else probsat_df[col].values

        return target_df

    def combine_csv(self, gsat_file, probsat_file, output_file):
        gsat = pd.read_csv(gsat_file, sep=config.DELIMITER)
        probsat = pd.read_csv(probsat_file, sep=config.DELIMITER)

        merged_df = self._merge_dataframes(gsat, probsat)
        merged_df.to_csv(output_file, sep=';', index=False)

    def process_files(self):
        for d in config.SETS:
            os.makedirs(os.path.join(config.MERGED, d), exist_ok=True)

        all_files = os.listdir(self.directory_path)
        probsat_files = [f for f in all_files if f.startswith('probSAT.')]
        gsat_files = [f for f in all_files if f.startswith('GSAT.')]

        for pf in probsat_files:
            matching_gsat = pf.replace('probSAT.', 'GSAT.')
            if matching_gsat in gsat_files:
                output_file = os.path.join(config.MERGED, f'{pf.split(".")[1]}/{pf.replace("probSAT.", "")}')
                self.combine_csv(os.path.join(self.directory_path, matching_gsat),
                                 os.path.join(self.directory_path, pf),
                                 output_file)
