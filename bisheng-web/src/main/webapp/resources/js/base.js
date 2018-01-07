/**
 * Created by chaowei.hu on 2016/3/3.
 */
(function () {
    // selectAll definition
    $.fn.selectAll = function (options) {
        // defaults settings
        var defaults = {
            globleId: 'selectAll',
            name: 'chb'
        };
        // initializes s settings
        var opts = $.extend(defaults, options);
        // implement
        $("#" + opts.globleId).unbind('click').click(function (e, el) {
            var c = $(this).is(':checked');
            $("[name='" + opts.name + "']").each(function () {
                $(this).attr("checked", c);
            });
        });
    };
    // trans definition
    $.extend({
        trans: function (options) {
            // defaults settings
            var defaluts = {};
            // initializes s settings
            var opts = $.extend({}, defaluts, options);
            var value = opts.key;
            // query the cache
            if ($.data($body_cache, opts.type)) {
                value = $.data($body_cache, opts.type)[opts.key];
            } else {
                // get from the hosts
                $.ajax({
                    type: "POST",
                    url: basePath + "/common/queryDicItemsByType.do",
                    dataType: "json",
                    data: {
                        dicType: opts.type
                    },
                    async: false,
                    success: function (data) {
                        if (data.resultCode == '0000') {
                            $.data($body_cache, opts.type, data.data);
                            value = data.data[opts.key];
                        }
                    }
                });
            }
            if (!value) value = opts.key;
            return value;
        },
        startMaskLayer: function () {
            //add MaskLayer on body
            $('body').addClass("maskLayer");
            $('body').append('<div class="page-loading"><img src="' + basePath + '/resources/img/loading-spinner-grey.gif"/><span>&nbsp;&nbsp;Loading...</span></div>');
        },
        endMaskLayer: function () {
            //remove MaskLayer on body
            $('.page-loading').remove();
            $('body').removeClass("maskLayer");
        },
        alert: function (message) {
            var $alert, css;
            $alert = $("<div>");
            $alert.attr("class", "bootstrap-growl alert alert-info alert-dismissible");
            $alert.append("<button  class=\"close\" data-dismiss=\"alert\" type=\"button\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>");
            $alert.append(message);
            css = {
                "position": "fixed",
                "margin": 0,
                "top": "60px",
                "z-index": "9999",
                "display": "none",
                "width": "auto",
                "min-width": "200px",
                "right": "100px"
            };
            $alert.css(css);
            var elem = $alert.insertAfter($("body"));
            elem.delay(100).fadeIn(300).delay(1500).fadeOut(1500);
        }
    });
})();

var myJson = {
    getText: function (key, json) {
        if (key == undefined || key == null) {
            return '';
        }

        var text = '';
        if (json == '') {
            return text;
        }

        json = JSON.parse(json);
        if (json[key]) {
            text = json[key];
            return text;
        } else {
            return '';
        }
    }
};

var myData = {
    notNull: function (value) {
        if (value == undefined || value == null) {
            return '';
        }
        return value;
    },

    toDate: function (value) {
        if (value == undefined || value == null || value == '') {
            return '';
        }
        var d = value.substr(0, 10);
        return d;
    },

    //开始时间不能小于当前时间
    startTimeIsBigThanToday: function (startTime) {
        var startDate = new Date((startTime).replace(/-/g, "/"));
        var today = new Date();
        var strDate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + (today.getDate());
        var date = new Date((strDate).replace(/-/g, "/"));
        if (startDate < date) {
            return true;
        } else {
            return false;
        }
    }
}
