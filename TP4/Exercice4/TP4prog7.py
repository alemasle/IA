import random

from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier

def make_brain():
    digits = load_digits()
    data = digits.data
    value = digits.target

    data_train, data_test, value_train, value_test = train_test_split(data, value, test_size=0.3, random_state=random.seed())

    clf = MLPClassifier(hidden_layer_sizes=9, random_state=1)
    clf.fit(data_train, value_train)

    score_test = clf.score(data_test, value_test)

    predict = clf.predict(data[:10])
    print(predict)

    print(score_test)


if __name__ == '__main__':
    make_brain()