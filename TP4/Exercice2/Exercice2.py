#!/usr/bin/python

from sklearn import neighbors
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix
import random
from sklearn.model_selection import KFold


def get_data(input_file):
    data = pd.read_csv(input_file)
    return data


def get_neighbor(data):
    features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
                     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
                     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

    pred_col = ["result"]

    turns_before_end = 4

    turn_df = data[data["turns_to_end"] == turns_before_end]
    X = turn_df[features_cols]
    Y = turn_df[pred_col]

    nb_neighb = 25
    clf = neighbors.KNeighborsClassifier(nb_neighb)
    clf.fit(X, Y)
    print(clf.predict(X[0:5]))
    print(clf.predict_proba(X[0:5]))
    print(clf.score(X, Y))
    Z = clf.predict(X)
    print(X[Z != Y["result"]])
    X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.3, random_state=random.seed())
    len(X_train)

    len(X_test)
    len(X_train[Y_train["result"] == 0])

    len(X_train[Y_train["result"] == 1])

    clf = clf.fit(X_train, Y_train)

    Y_pred = clf.predict(X_test)
    cm = confusion_matrix(Y_test, Y_pred)

    print(cm)

    kf = KFold(n_splits=10, shuffle=False)
    for learn, test in kf.split(X):
        print("app : ", learn, " test ", test)


if __name__ == '__main__':
    data = get_data("../full_dataset.csv")
    get_neighbor(data)
