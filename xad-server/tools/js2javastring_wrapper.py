"""
This module provide gen constant class
"""
import sys
import string
import os
print '''package com.baidu.mobads.container.template;

public class XAdMRAIDWrapperConstants {
    public static final String XAD_MRAID_INJECTION_JS = ""  '''
for line in sys.stdin:
    one = string.replace(line, '"', '\\"').rstrip(os.linesep)
    print '\t  + "' + one + ' \\n"'
print ';'
print '}'
