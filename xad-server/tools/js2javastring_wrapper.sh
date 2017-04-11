content=mraid_wrapper_4_template.js

if [ "$1" == "min" ]; then
	java -jar ./tools/js/closure-compiler/compiler.jar --js mraid_wrapper_4_template.js  > content_min.js
	content=content_min.js
fi

cat $content | python js2javastring_wrapper.py > ./xadsdk_lib_remote/src/com/baidu/mobads/container/template/XAdMRAIDWrapperConstants.java
rm content_min.js >& /dev/null

