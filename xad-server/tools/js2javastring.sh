mraid=mraid.js

if [ "$1" == "min" ]; then
	java -jar ./tools/js/closure-compiler/compiler.jar --js mraid.js  > mraid_min.js
	mraid=mraid_min.js
fi

cat $mraid | python js2javastring.py > ./xadsdk_lib_remote/src/com/baidu/mobads/container/template/XAdMRAIDConstants.java
rm mraid_min.js >& /dev/null

