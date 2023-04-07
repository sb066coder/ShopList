package ru.sb066coder.shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.sb066coder.shoplist.R
import ru.sb066coder.shoplist.domain.ShopItem

class ShopItemActivity: AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilAmount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etAmount: EditText
//    private lateinit var btnSave: Button
//
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initializeViews()
        launchRightMode()
//        setupErrorObservers()
//        viewModel.closeScreen.observe(this) {
//            finish()
//        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD  -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else      -> throw RuntimeException("Unknown parameter screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

    //    private fun launchAddMode() {
//        btnSave.setOnClickListener {
//            viewModel.addShopItem(etName.text.toString(), etAmount.text.toString())
//        }
//    }
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.currentShopItem.observe(this) {
//            etName.setText(it.name)
//            etAmount.setText(it.amount.toString())
//        }
//        btnSave.setOnClickListener {
//            viewModel.updateShopItem(etName.text.toString(), etAmount.text.toString())
//        }
//    }
//
//    private fun setupErrorObservers() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.enter_item_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//        })
//        viewModel.errorInputAmount.observe(this) {
//            val message = if (it) {
//                 getString(R.string.enter_amount_more_than_zero)
//            } else {
//                null
//            }
//            tilAmount.error = message
//        }
//        etAmount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputAmount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//        })
//    }
//
    private fun parseIntent() {
        screenMode = intent.getStringExtra(EXTRA_SCREEN_MODE) ?: throw RuntimeException(
            "Parameter screen mode is absent"
        )
        if (screenMode != MODE_ADD && screenMode != MODE_EDIT) {
            throw RuntimeException("Unknown parameter screen mode $screenMode")
        }
        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
            throw RuntimeException("Parameter item id is absent")
        } else if (screenMode == MODE_EDIT) {
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }
//
//    private fun initializeViews() {
//        tilName = findViewById(R.id.til_name)
//        etName = findViewById(R.id.et_name)
//        tilAmount = findViewById(R.id.til_amount)
//        etAmount = findViewById(R.id.et_amount)
//        btnSave = findViewById(R.id.btn_save)
//    }
//
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }
    }
}