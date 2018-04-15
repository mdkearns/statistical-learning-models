# -*- coding: utf-8 -*-
"""
Date: 2018-04-14

@author: Matthew Kearns
"""

import sys
import numpy as np
from preprocessing import read_data as rd
from numpy.linalg import inv

__author__ = "Matthew Kearns"

def main():
    """This program perfoms linear regression via the Normal Equation."""
    
    # get the name of the training data file
    
    train_data_file = sys.argv[1]
    
    # parse inputs and outputs from training data set
    
    X, y = rd.read_numerical_data(train_data_file, output_row=0)
    
    # perform linear regression via the Normal Equation
    
    theta = np.dot(inv(np.dot(np.transpose(X), X)), np.dot(np.transpose(X), y))
    
    # display results of regression
    
    print("\nLinear model for the", train_data_file, "training set: \n\n")
    print(str(theta[0]), "+\n",  str(theta[1]) + "*X_1", "+\n", str(theta[2]) + "*X_2", "+\n", str(theta[3]) + "*X_3\n")
    
    # evaluate prediction quality
    
    print(np.dot(X[5], theta))
    print(y[5])
    
    return 0

if __name__ == '__main__':
    main()
