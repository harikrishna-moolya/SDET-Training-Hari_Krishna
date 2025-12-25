import { test, expect } from '@playwright/test';

test('Registration form handling', async ({ page }) => {

  await page.goto(
    'https://tutorialsninja.com/demo/index.php?route=account/register'
  );

  await page.fill('#input-firstname', 'Hari');
  await page.fill('#input-lastname', 'Krishna');
  await page.fill('#input-email', `hk${Date.now()}@gmail.com`);
  await page.fill('#input-telephone', '9876543210');
  await page.fill('#input-password', 'Test@123');
  await page.fill('#input-confirm', 'Test@123');

  await page.check('input[name="agree"]');

  await page.screenshot({ path: 'screenshots/before-submit.png' });

  await page.click('input[value="Continue"]');

  await expect(
    page.getByRole('heading', { name: 'Your Account Has Been Created!' })
  ).toBeVisible();
});
