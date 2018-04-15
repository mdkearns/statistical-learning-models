# -*- coding: utf-8 -*-
"""
Date: 2018-04-14

@author: Matthew Kearns
"""

import numpy as np

def read_numerical_data(file_name, output_row, delimiter=','):
    """This function separates real-valued inputs and outputs."""
    
    X = list()
    y = list()
    
    with open(file_name, 'r') as file:
        
        data = file.readlines()
        
        for line in data:
            
            datum = line.split(delimiter)  #parse by delimiter
            datum[-1] = datum[-1][:-1]     #remove newline character
                
            X.append(datum[:output_row] + datum[output_row+1:])
            y.append(datum[output_row])
                
    X = np.array(X, dtype=float)
    y = np.array(y, dtype=float)
            
    return X, y
    
def read_categorical_data(file_name, output_row=None):
    """This function separates discrete inputs and outputs."""
    
    X = list()
    y = list()
    
    return X, y
    
def read_test_data(file_name, categorical=False, has_outputs=False, output_row=None):
    """This function reads numerical or categorical features."""
    
    X = list()
    
    return X
