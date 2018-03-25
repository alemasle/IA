import pandas as pd
from matplotlib import pyplot as plt
from numpy.random import random


def lecsv(datafile):
    data = pd.read_csv(datafile)
    print(data[["my_health", "my_hand"]][0:5])


def cara(datafile):
    data = pd.read_csv(datafile)
    turns_before_end = 4
    turn_df = data[data["turns_to_end"] == turns_before_end]
    print(turn_df)
    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]

    X = turn_df[features_cols]
    Y = turn_df[pred_col]
    print(X)
    print(Y)
    printrigolo(X, Y)
    printprecis(X, Y)


def printrigolo(X, Y):
    x_col = "my_health"
    y_col = "opponent_health"
    plt.scatter(X[x_col], X[y_col], Y)
    plt.show()
    plt.xlabel(x_col)

    plt.ylabel(y_col)
    plt.title("Hearthstone data: health")

    plt.scatter(X[x_col], X[y_col], Y)

    plt.show()


def printprecis(X, Y):
    x_col = "my_health"
    y_col = "opponent_health"
    print(Y["result"] == 0)
    print(X[Y["result"] == 0])
    plt.scatter(X[Y["result"] == 0][x_col], X[Y["result"] == 0][y_col], color="red", label="lose")
    plt.scatter(X[Y["result"] == 1][x_col], X[Y["result"] == 1][y_col], color="green", label="win")
    plt.legend()
    plt.show()


def funcfin():
    data = pd.read_csv("../full_dataset.csv")

    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]

    turns_before_end = 4

    turn_df = data[data["turns_to_end"] == turns_before_end]
    X = turn_df[features_cols]
    Y = turn_df[pred_col]

    x_col = "my_health"
    y_col = "opponent_health"

    colors = ["red", "green"]
    for i in range(2):
        indices = Y["result"] == i
        plt.scatter(X[indices][x_col] + 0.5 * (random(X[indices].shape[0]) - 0.5),
                    X[indices][y_col] + 0.5 * (random(X[indices].shape[0]) - 0.5),
                    color=colors[i], label=i, s=2)
    plt.legend()
    plt.xlabel(x_col)
    plt.ylabel(y_col)
    plt.title("Hearthstone data: health")
    plt.show()


if __name__ == '__main__':
    data_file = "../full_dataset.csv"
    #cara(data_file)
    funcfin()