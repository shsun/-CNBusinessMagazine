# coding=utf-8
"""
Script for png & string converting
"""

import base64
import os  


def png2string(img_path): 
    """
    convert png to string
    """
    f = open(img_path, "rb")
    data = f.read()
    f.close()
    
    str = base64.b64encode(data)
    #print '\n********'
    #print img_path
    #print str

    return str


def string2png(str, image_path):
    """
    convert string 2 png and svae it
    """
    data = base64.b64decode(str)
    f = open(image_path, "w")
    f.write(data)
    f.close()

    
if __name__ == "__main__":
    dir_path = "xadsdk_lib_proxy/resource"
    for file_name in os.listdir(dir_path):
        full_path = dir_path + "/" + file_name
        print full_path
        str = png2string(full_path)



