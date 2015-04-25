'use strict';

describe('Directive: directives/utils/i18n', function () {

  // load the directive's module
  beforeEach(module('snowApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<directives/utils/i18n></directives/utils/i18n>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the directives/utils/i18n directive');
  }));
});
