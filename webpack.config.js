import MiniCssExtractPlugin from 'mini-css-extract-plugin'
import path from 'path'
import fs from 'fs'
import { fileURLToPath } from 'url'

const dirname = path.dirname(fileURLToPath(import.meta.url))
const entries = {}

fs.readdirSync('./src/main/js/')
    .filter((fileName) => fileName.match(/.*\.js$/))
    .forEach((fileName) => {
        entries[fileName.replace(/\.js$/, '')] = [`./src/main/js/${fileName}`]
    })

const config = {
    entry: entries,
    output: {
        filename: 'bundle-[name].js',
        path: path.resolve(dirname, 'src/main/resources/static/js'),
        clean: true
    },
    devtool: 'source-map',
    mode: 'development',
    plugins: [
        new MiniCssExtractPlugin({
            filename: '../css/bundle-[name].css'
        })
    ],
    module: {
        rules: [
            {
                test: /\.s?css$/i,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader'
                ]
            },
            {
                test: /\.(png|svg|jpe?g|gif)$/i,
                type: 'asset'
            },
            {
                test: /\.(woff2?|eot|ttf|otf)$/i,
                type: 'asset'
            }
        ]
    },
    experiments: {
        topLevelAwait: true
    }
}

export default config
