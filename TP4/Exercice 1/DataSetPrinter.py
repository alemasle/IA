import pandas as pd
from matplotlib import pyplot as plt


def getdata(inputfile):
    data = pd.read_csv(inputfile)
    return data


def printdata(data):
    print(len(data))
    print('-\n')
    print(data.columns[0])
    print('-\n')
    print(data.shape)
    print('-\n')
    print(data.iloc[0])
    print('-\n')
    print(data[0:5])
    print('-\n')
    print(data["my_health"])
    print('-\n')
    print(data[["my_health", "my_hand"]][0:5])


def print_nb_turn(data):
    turns_before_end = 4
    turn_df = data[data["turns_to_end"] == turns_before_end]
    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]
    X = turn_df[features_cols]
    Y = turn_df[pred_col]
    x_col = "my_health"
    y_col = "opponent_health"
    plt.scatter(X[x_col], X[y_col], c=Y)
    plt.show()


if __name__ == '__main__':
    data = getdata("../full_dataset.csv")
    #printdata(data)
    print_nb_turn(data)