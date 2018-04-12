#!/usr/bin/python

import pandas as pd
from sklearn import tree

def get_data(input_file):
    data = pd.read_csv(input_file)
    return data


def make_tree(data):
    ret_tree = tree.DecisionTreeClassifier(max_leaf_nodes=5)

    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]
    pred_col = ["result"]

    turns_before_end = 4
    turn_df = data[data["turns_to_end"] == turns_before_end]
    X = turn_df[features_cols]
    Y = turn_df[pred_col]

    ret_tree = ret_tree.fit(X, Y)
    tree.export_graphviz(ret_tree, out_file='tree_3.dot', feature_names=features_cols)


if __name__ == '__main__':
    data = get_data("../full_dataset.csv")
    make_tree(data)