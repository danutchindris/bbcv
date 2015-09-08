/**
 * Bootstrap Table Romanian translation
 * Author: cristake <cristianiosif@me.com>
 */
(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['ro-RO'] = {
        formatLoadingMessage: function () {
            return 'Se încarcă, vă rugăm așteptați...';
        },
        formatRecordsPerPage: function (pageNumber) {
            return pageNumber + ' înregistrări pe pagină';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return 'Arată de la ' + pageFrom + ' până la ' + pageTo + ' din ' + totalRows + ' rânduri';
        },
        formatSearch: function () {
            return 'Caută';
        },
        formatNoMatches: function () {
            return 'Nu au fost găsite înregistrări';
        },
        formatPaginationSwitch: function () {
            return 'Ascunde/Arată paginația';
        },
        formatRefresh: function () {
            return 'Reîncarcă';
        },
        formatToggle: function () {
            return 'Comută';
        },
        formatColumns: function () {
            return 'Coloane';
        },
        formatAllRows: function () {
            return 'Toate';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['ro-RO']);

})(jQuery);