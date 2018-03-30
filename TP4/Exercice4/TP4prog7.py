from sklearn.datasets import load_digits
from sklearn.neural_network import MLPClassifier

def make_brain():
    digits=load_digits()
    data = digits.data
    value = digits.target

    clf = MLPClassifier(hidden_layer_sizes=(5,4,3))
    clf.fit(data, value)
    predict = clf.predict(data[:10])
    print(predict)


if __name__ == '__main__':
    make_brain()