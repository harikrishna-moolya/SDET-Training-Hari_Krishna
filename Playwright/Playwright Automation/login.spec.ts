import { test, expect } from '@playwright/test';

test.describe('TutorialsNinja Login Flow', () => {

  test('User should be able to login successfully', async ({ page }) => {

    await page.goto('https://tutorialsninja.com/demo/index.php?route=account/login');

    await page.fill('#input-email', 'hk01@gmail.com');
    await page.fill('#input-password', '1432');

    await page.click('input[value="Login"]');

    await expect(page).toHaveURL(/route=account\/account/);

    await expect(
      page.locator('#content').getByRole('heading', { name: 'My Account' })
    ).toBeVisible();

  });

});
