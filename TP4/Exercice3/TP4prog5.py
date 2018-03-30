import pandas as pd
from sklearn import tree
from sklearn.model_selection import train_test_split
from matplotlib import pyplot as plt
import random


def get_data(input_file):
    data = pd.read_csv(input_file)
    return data


def make_tree(data):
    scores_train = []
    scores_test = []
    name = range(1, 51)
    for i in name:
        score_train = 0
        score_test = 0
        ret_tree = tree.DecisionTreeClassifier(max_leaf_nodes=50 * i)

        features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]
        pred_col = ["result"]

        turns_before_end = 4
        turn_df = data[data["turns_to_end"] == turns_before_end]
        X = turn_df[features_cols]
        Y = turn_df[pred_col]

        X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.3, random_state=random.seed())

        ret_tree = ret_tree.fit(X_train, Y_train)
        score_train = ret_tree.score(X_train, Y_train)
        score_test = ret_tree.score(X_test, Y_test)
        scores_test.append(score_test)
        scores_train.append(score_train)
        tree.export_graphviz(ret_tree, out_file="dot_prog5/tree{}.dot".format(i), feature_names=features_cols)
    plt.plot(name, scores_train)
    plt.show()
    plt.plot(name, scores_test)
    plt.show()

if __name__ == '__main__':
    data = get_data("../full_dataset.csv")
    make_tree(data)