import random
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier


def get_data(input_file):
    data = pd.read_csv(input_file)
    return data


def make_brain(data_csv):

    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]
    for i in range(4, 0, -1):
        turns_before_end = i
        print(turns_before_end)

        turn_df = data_csv[data_csv["turns_to_end"] == turns_before_end]
        data = turn_df[features_cols]
        value = turn_df[pred_col]

        data_train, data_test, value_train, value_test = train_test_split(data, value, test_size=0.3,
                                                                      random_state=random.seed())

        clf = MLPClassifier(hidden_layer_sizes=(5, 4), random_state=1)
        clf.fit(data_train, value_train)

        score_test = clf.score(data_test, value_test)

        predict = clf.predict(data[:10])
        print(predict)

        print(score_test)


if __name__ == '__main__':
    make_brain(get_data("../full_dataset.csv"))