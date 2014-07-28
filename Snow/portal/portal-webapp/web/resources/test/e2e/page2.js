/**
 * Created with IntelliJ IDEA.
 * User: damon
 * Date: 14-7-25
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
describe('my app', function() {

    browser.get('/');

    it('should automatically redirect to /view1 when location hash/fragment is empty', function() {
        expect(browser.getLocationAbsUrl()).toMatch("/#/");
    });


    describe('view about', function() {

        beforeEach(function() {
            browser.get('/#/about');
        });

        it('should render view1 when user navigates to #/about', function() {
            expect(browser.getLocationAbsUrl()).toMatch("/#/about");
        });

    });
});