package com.hifive.model.constant;


import java.nio.charset.Charset;
import java.util.Set;

public final class StringConstants {

    // disable constructor
    private StringConstants() {
    }

    // ---------------------------------- 来自于 bst --------------------------------
    // ---------------------------------- 通用常用常量 --------------------------------
    /**
     * 空白字符串
     */
    public static final String EMPTY_STR = "";
    /**
     * crlf
     */
    public static final String CRLF = "\r\n";
    /**
     * null
     */
    public static final String NULL = null;
    /**
     * null
     */
    public static final String NULL_STR = "null";
    /**
     * 默认的值
     */
    public static final String DEFAULT_VALUE = "--";
    /**
     * constants
     */
    public static final String CONSTANTS = "constants";
    /**
     * handler
     */
    public static final String HANDLER = "handler";
    /**
     * true
     */
    public static final String TRUE = Boolean.TRUE.toString();
    /**
     * false
     */
    public static final String FALSE = Boolean.FALSE.toString();

    /**
     * \
     */
    public static final Character SLASH = '\\';
    /**
     * /
     */
    public static final Character INV_SLASH = '/';
    /**
     * ?
     */
    public static final Character QUESTION = '?';
    /**
     * .
     */
    public static final Character DOT = '.';
    /**
     * ,
     */
    public static final Character COMMA = ',';
    /**
     * :
     */
    public static final Character COLON = ':';
    /**
     *
     */
    public static final Character SPACE = ' ';
    /**
     * \t
     */
    public static final Character TAB = '\t';
    /**
     * \r
     */
    public static final Character CR = '\r';
    /**
     * \n
     */
    public static final Character LF = '\n';
    /**
     * "
     */
    public static final Character QUOTE = '\"';
    /**
     * '
     */
    public static final Character SINGLE_QUOTE = '\'';


    // ---------------------------------- 通用业务上下文常量 --------------------------------
    /**
     * task
     */
    public static final String TASK = "task";
    /**
     * site
     */
    public static final String SITE = "site";
    /**
     * name
     */
    public static final String NAME = "name";
    /**
     * url
     */
    public static final String URL = "url";
    /**
     * pageNo
     */
    public static final String PAGE_NO = "pageNo";
    /**
     * keyWord
     */
    public static final String KEY_WORD = "keyWord";
    /**
     * debugEnable
     */
    public static final String DEBUG_ENABLE = "debugEnable";
    /**
     * fetchedResult
     */
    public static final String FETCHED_RESULT = "fetchedResult";
    /**
     * spent
     */
    public static final String SPENT = "spent";
    /**
     * depth
     */
    public static final String DEPTH = "depth";
    /**
     * request
     */
    public static final String REQUEST = "request";
    /**
     * response
     */
    public static final String RESPONE = "response";
    /**
     * reason
     */
    public static final String REASON = "reason";
    /**
     * message
     */
    public static final String MSG = "message";
    /**
     * ext
     */
    public static final String EXT = "ext";
    /**
     * bucket
     */
    public static final String BUCKET = "bucket";
    /**
     * bin
     */
    public static final String BIN = "bin";
    // add at 2016.06.09
    /**
     * filter
     */
    public static final String FILTER = "filter";
    /**
     * assert
     */
    public static final String ASSERT = "assert";
    // add at 2016.03.21
    /**
     * brand
     */
    public static final String BRAND = "brand";
    /**
     * universalProductCode
     */
    public static final String UPC = "universalProductCode";
    /**
     * manufacturePartNumber
     */
    public static final String MPN = "manufacturePartNumber";
    /**
     * level
     */
    public static final String LEVEL = "level";
    /**
     * value
     */
    public static final String VALUE = "value";
    /**
     * description
     */
    public static final String DESCRIPTION = "description";
    /**
     * list
     */
    public static final String LIST = "list";
    /**
     * rebate
     */
    public static final String REBATE = "rebate";
    /**
     * final
     */
    public static final String FINAL = "final";
    /**
     * price
     */
    public static final String PRICE = "price";


    // ---------------------------------- 通用 http 协议相关常量 --------------------------------
    /**
     * get
     */
    public static final String GET = "get";
    /**
     * post
     */
    public static final String POST = "post";
    /**
     * put
     */
    public static final String PUT = "put";
    /**
     * delete
     */
    public static final String DELETE = "delete";
    /**
     * header
     */
    public static final String HEADER = "header";
    /**
     * trace
     */
    public static final String TRACE = "trace";

    /**
     * Cookie
     */
    public static final String COOKIE_STR = "Cookie";
    /**
     * Set-Cookie
     */
    public static final String RESP_COOKIE_STR = "Set-Cookie";
    /**
     * Content-Type
     */
    public static final String CONTENT_TYPE = "Content-Type";
    /**
     * Content-Encoding
     */
    public static final String CONTENT_ENCODING = "Content-Encoding";
    /**
     * Accept
     */
    public static final String ACCEPT = "Accept";
    /**
     * Accept-Encoding
     */
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    /**
     * Accept-Language
     */
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    /**
     * Cache-Control
     */
    public static final String CACHE_CONTROL = "Cache-Control";
    /**
     * Connection
     */
    public static final String CONNECTION = "Connection";
    /**
     * Host
     */
    public static final String HOST = "Host";
    /**
     * Referer
     */
    public static final String REFERER = "Referer";
    /**
     * User-Agent
     */
    public static final String USER_AGENT = "User-Agent";
    /**
     * Date
     */
    public static final String DATE = "Date";
    /**
     * Server
     */
    public static final String SERVER = "Server";
    /**
     * Transfer-Encoding
     */
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    /**
     * Last-Modified
     */
    public static final String LAST_MODIFIED = "Last-Modified";
    /**
     * If-Modified-Since
     */
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

//	 常见的媒体格式类型如下：
//	    text/html ： HTML格式
//	    text/plain ：纯文本格式
//	    text/xml ：  XML格式
//	    image/gif ：gif图片格式
//	    image/jpeg ：jpg图片格式
//	    image/png：png图片格式
//	   以application开头的媒体格式类型：
//	   application/xhtml+xml ：XHTML格式
//	   application/xml     ： XML数据格式
//	   application/atom+xml  ：Atom XML聚合格式
//	   application/json    ： JSON数据格式
//	   application/pdf       ：pdf格式
//	   application/msword  ： Word文档格式
//	   application/octet-stream ： 二进制流数据（如常见的文件下载）
//	   application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
//	   另外一种常见的媒体格式是上传文件之时使用的：
//	    multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式
//	     以上就是我们在日常的开发中，经常会用到的若干content-type的内容格式。
    /**
     * text/html
     */
    public static final String TEXT_HTML = "text/html";
    /**
     * text/plain
     */
    public static final String TEXT_PLAIN = "text/plain";
    /**
     * text/xml
     */
    public static final String TEXT_XML = "text/xml";
    /**
     * text/gif
     */
    public static final String TEXT_GIF = "text/gif";
    /**
     * text/jpeg
     */
    public static final String TEXT_JPEG = "text/jpeg";
    /**
     * text/png
     */
    public static final String TEXT_PNG = "text/png";
    /**
     * application/xhtml+xml
     */
    public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
    /**
     * application/xml
     */
    public static final String APPLICATION_XML = "application/xml";
    /**
     * application/atom+xml
     */
    public static final String APPLICATION_ATOM_XML = "application/atom+xml";
    /**
     * application/json
     */
    public static final String APPLICATION_JSON = "application/json";
    /**
     * application/pdf
     */
    public static final String APPLICATION_PDF = "application/pdf";
    /**
     * application/msword
     */
    public static final String APPLICATION_MS_WORD = "application/msword";
    /**
     * application/octet-stream
     */
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    /**
     * application/x-www-form-urlencoded
     */
    public static final String APPLICATION_URL_ENCODED = "application/x-www-form-urlencoded";
    /**
     * multipart/form-data
     */
    public static final String MULTI_PART_FORM_DATA = "multipart/form-data";


    // ---------------------------------- 通用 后缀相关常量 --------------------------------
    /**
     * .html
     */
    public static final String HTML = ".html";
    /**
     * .java
     */
    public static final String JAVA = ".java";
    /**
     * .scala
     */
    public static final String SCALA = ".scala";
    /**
     * .py
     */
    public static final String PYTHON = ".py";
    // add at 2016.05.13
    /**
     * .h
     */
    public static final String C_HEADER = ".h";
    /**
     * .c
     */
    public static final String C_SOURCE = ".c";
    /**
     * .cpp
     */
    public static final String CPP = ".cpp";
    /**
     * .php
     */
    public static final String PHP = ".php";
    /**
     * .txt
     */
    public static final String TXT = ".txt";
    /**
     * .png
     */
    public static final String PNG = ".png";
    /**
     * .jpg
     */
    public static final String JPG = ".jpg";
    /**
     * .jpeg
     */
    public static final String JPEG = ".jpeg";
    /**
     * .js
     */
    public static final String JS = ".js";
    /**
     * .map
     */
    public static final String MAP = ".map";
    /**
     * .zip
     */
    public static final String ZIP = ".zip";
    /**
     * .idx
     */
    public static final String IDX = ".idx";
    /**
     * .fiv
     */
    public static final String FIV = ".fiv";
    /**
     * .mp4
     */
    public static final String MP4 = ".mp4";
    /**
     * .3gp
     */
    public static final String GP3 = ".3gp";
    /**
     * .rmvb
     */
    public static final String RMVB = ".rmvb";
    /**
     * .rm
     */
    public static final String RM = ".rm";
    /**
     * .avi
     */
    public static final String AVI = ".avi";
    /**
     * .log
     */
    public static final String LOG = ".log";
    /**
     * .conf			-- add at 2016.06.28
     */
    public static final String CONF = ".conf";
    /**
     * .class			-- add at 2016.05.13
     */
    public static final String CLASS = ".class";
    /**
     * .doc
     */
    public static final String DOC = ".doc";
    /**
     * .docx
     */
    public static final String DOCX = ".docx";
    /**
     * .xls
     */
    public static final String XLS = ".xls";
    /**
     * .xlsx
     */
    public static final String XLSX = ".xlsx";
    /**
     * .ppt
     */
    public static final String PPT = ".ppt";
    /**
     * .pptx
     */
    public static final String PPTX = ".pptx";


    // ---------------------------------- 通用 编码相关常量 --------------------------------
    /**
     * ascii
     */
    public static final String ASCII = "ascii";
    /**
     * iso-8859-1
     */
    public static final String ISO_8859_1 = "iso-8859-1";
    /**
     * utf-8
     */
    public static final String UTF_8 = "utf-8";
    /**
     * utf-16
     */
    public static final String UTF_16 = "utf-16";
    /**
     * gbk
     */
    public static final String GBK = "gbk";
    /**
     * gb2312
     */
    public static final String GB2312 = "gb2312";


    // ---------------------------------- 通用 字节的表示相关常量 --------------------------------
    /**
     * byte
     */
    public static final String BYTE = "byte";
    /**
     * kb
     */
    public static final String KB = "kb";
    /**
     * mb
     */
    public static final String MB = "mb";
    /**
     * gb
     */
    public static final String GB = "gb";
    /**
     * tb
     */
    public static final String TB = "tb";
    /**
     * pb
     */
    public static final String PB = "pb";
    /**
     * eb
     */
    public static final String EB = "eb";
    /**
     * zb
     */
    public static final String ZB = "zb";
    /**
     * yb
     */
    public static final String YB = "yb";

    /**
     * 默认的字符集
     * utf-8 or gbk denpend on container
     */
    public static final String DEFAULT_CHARSET = Charset.defaultCharset().name();

    // ---------------------------------- 通用 十六进制的表示相关常量 --------------------------------

    /**
     * 十六进制的字符, 默认的返回字符串[编码的过程中发生了Exception]
     */
    public static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    // a-z 小写
    public static final char HEX_DIGITS_LOWER_CASE[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // ---------------------------------- 通用 十进制进制的表示相关常量 --------------------------------

    /**
     * -1
     */
    public static final String NEGATE_ONE = "-1";
    /**
     * 0
     */
    public static final String ZERO = "0";
    /**
     * 1
     */
    public static final String ONE = "1";
    /**
     * 2
     */
    public static final String TWO = "2";
    /**
     * 3
     */
    public static final String THREE = "3";
    /**
     * 4
     */
    public static final String FOUR = "4";
    /**
     * 5
     */
    public static final String FIVE = "5";
    /**
     * 6
     */
    public static final String SIX = "6";
    /**
     * 7
     */
    public static final String SEVEN = "7";
    /**
     * 8
     */
    public static final String EIGHT = "8";
    /**
     * 9
     */
    public static final String NINE = "9";
    /**
     * 10
     */
    public static final String TEN = "10";
    /**
     * 100
     */
    public static final String HUNDRED = "100";
    /**
     * 1000
     */
    public static final String THOUSAND = "1000";


}
