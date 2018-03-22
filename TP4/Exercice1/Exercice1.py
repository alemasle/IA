#!/usr/bin/python3

import pandas as pd
from matplotlib import pyplot as plt


def lecsv(datafile):
    data = pd.read_csv(datafile)
    print(data[["my_health", "my_hand"]][0:5])


def cara(datafile):
    data = pd.read_csv(datafile)
    turns_before_end = 4
    turn_df = data[data["turns_to_end"] == turns_before_end]
    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]

    X = turn_df[features_cols]
    Y = turn_df[pred_col]
    print(X)
    print(Y)
    printrigolo(X, Y)


def printrigolo(X, Y):
    x_col = "my_health"
    y_col = "opponent_health"
    plt.scatter(X[x_col], X[y_col], Y)
    plt.show()

    help(plt.scatter)
    plt.xlabel(x_col)

    plt.ylabel(y_col)
    plt.title("Hearthstone data: health")

    plt.scatter(X[x_col], X[y_col], Y)

    plt.show()


if __name__ == '__main__':
    data_file = "../full_dataset.csv"
    cara(data_file)
