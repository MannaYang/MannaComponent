package com.manna.component.module_mine;

import android.widget.ImageView;
import android.widget.TextView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.manna.component.library_base.base.BaseActivity;
import com.seek.biscuit.Biscuit;
import com.seek.biscuit.CompressException;
import com.seek.biscuit.CompressListener;

import java.util.ArrayList;

public class TestMineActivity extends BaseActivity {

    private TextView tvStartImage;
    private ImageView ivImage;

    @Override
    public int getLayoutId() {
        return R.layout.module_mine_activity_test_mine;
    }

    @Override
    public void initView() {

        tvStartImage = findViewById(R.id.tv_start_image);
        ivImage = findViewById(R.id.iv_image);

        //FileProvider需针对自己的项目配置
        tvStartImage.setOnClickListener(v -> {
            EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                    .setFileProviderAuthority("com.manna.component.FileProvider")
                    .setCount(1)
                    .start(new SelectCallback() {
                        @Override
                        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
                            compressImage(paths.get(0));
                        }
                    });
        });
    }

    /**
     * 压缩图片
     *
     * @param path ：图片地址
     */
    private void compressImage(String path) {
        Biscuit.with(this)
                .path(path) //可以传入一张图片路径，也可以传入一个图片路径列表进行批量压缩
                .loggingEnabled(true)//是否输出log 默认输出
                .originalName(true) //使用原图名字来命名压缩后的图片，默认不使用原图名字,随机图片名字
                .listener(new CompressListener() {
                    @Override
                    public void onSuccess(String compressedPath) {
                        tvStartImage.setText("图片压缩地址 :" + compressedPath);
                        GlideEngine.getInstance().loadPhoto(TestMineActivity.this, compressedPath,
                                ivImage);
                    }

                    @Override
                    public void onError(CompressException e) {
                        e.printStackTrace();
                    }
                })//压缩监听,每压缩完成一张即回调一次监听
//                .listener(mOnCompressCompletedListener)//压缩完成监听，只有传入的所有图片都压缩结束才回调
                .ignoreLessThan(100)//忽略小于100kb的图片不压缩，返回原图路径
                .build()
                .asyncCompress();//异步压缩
    }
}
