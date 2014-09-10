'use strict';

###*
 * @ngdoc directive
 * @name snowApp.directive:i18nDirective
 * @description
 * # i18nDirective
###
console.log "Excute i18nDirective"
angular.module('snowApp').directive('i18n',(i18n)->
    {
        restrict: 'EA',
        link: (scope, element, attrs)-> element.text(i18n[attrs['i18n']])
    }
)
