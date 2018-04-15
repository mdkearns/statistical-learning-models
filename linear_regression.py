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
    
    # evaluate prediction quality
    
    raw_score = 0
    tolerance = 10
    
    for i in range(len(X)):
        
        pred = np.dot(X[i], theta)
        
        if y[i]-tolerance <= pred <= y[i]+tolerance:
            raw_score += 1
            
    average = raw_score/len(X)
    
    # report prediction quality
    
    print()
    print("Data Set:\t", train_data_file, "\n")
    print("Linear Model:\t y_hat = theta_0 + theta_1 * X_1 + ... + theta_n * X_n\n")
    print("Theta:\t\t", theta, "\n")
    print(str(average*100) + "%", "of the training data was correctly classified with a tolerance of +/-", str(tolerance) + ".")
    print()
    
    return 0

if __name__ == '__main__':
    main()
