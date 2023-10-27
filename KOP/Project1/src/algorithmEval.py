import os
import json
import subprocess
import multiprocessing

import config


class AlgorithmEvaluator:
    def __init__(self, program_path, data_path, results_path=config.RESULTS):
        self.program_path = program_path
        self.data_path = data_path
        self.results_path = results_path
        self.default_args = ['-r', 'time', '-e', ';']

    def _run_program(self, input_file, output_file, filename, *args):
        solved = total_steps = solved_steps = 0
        cmd = [self.program_path, *args, *self.default_args]

        with open(input_file, 'r') as file:
            for _ in range(config.REPS):
                file.seek(0)
                result = subprocess.run(cmd, stdin=file, capture_output=True, text=True)
                a, b, c, d = map(int, result.stderr.split(config.DELIMITER))

                if a <= b and c == d:
                    solved += 1
                    total_steps += a
                    solved_steps += a
                else:
                    total_steps += b * 10

        with open(output_file, 'a') as out_file:
            out_file.write(f'{filename};{solved};{config.REPS};{total_steps/config.REPS};{solved_steps/solved}\n')

    def _process_directory(self, conf_set, dirpath, program_name):
        for params in conf_set["args"]:
            args = [str(val) for pair in params.items() for val in pair]
            res_file_name = f'{program_name}.{os.path.basename(dirpath)}_SET.{"_".join(args)}_CONF.csv'

            with open(os.path.join(self.results_path, res_file_name), 'w') as file:
                file.write(
                    f'Instance;'
                    f'Solved {program_name};'
                    f'Total;'
                    f'Avg fined steps {program_name};'
                    f'Avg steps of solved {program_name}\n'
                )

            for filename in os.listdir(dirpath):
                self._run_program(os.path.join(dirpath, filename), os.path.join(self.results_path, res_file_name),
                                  filename, *args)

    def run(self, program_name):
        with open(f'{config.ROOT_DIR}/configuration.json', 'r') as f:
            data = json.load(f)

        directories = [os.path.join(self.data_path, d) for d in os.listdir(self.data_path) if
                       os.path.isdir(os.path.join(self.data_path, d))]
        processes = []

        for dirpath in directories:
            conf_set = data[os.path.basename(dirpath)]
            p = multiprocessing.Process(target=self._process_directory, args=(conf_set, dirpath, program_name))
            processes.append(p)
            p.start()

        for process in processes:
            process.join()
